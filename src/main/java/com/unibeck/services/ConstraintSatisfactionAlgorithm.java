package com.unibeck.services;

import com.unibeck.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.unibeck.model.Percentiles.convertFromWithDouble;
import static com.unibeck.model.Percentiles.convertFromWithInt;

@Service
public class ConstraintSatisfactionAlgorithm {

    public List<Smartphone> findClosestMatching(UserConstraint userConstraint, List<Smartphone> allSmartphones) {
        List<Constraint> constraints = buildConstraints(userConstraint); //All of the constraints and their values
        ConstraintTree tree = buildConstraintTree(constraints.get(0), constraints); //Tree with all permutations of constraints

        List<Smartphone> remainder = new ArrayList<>();
        int max = 0, temp = 0;

        for(int i = 0; i < allSmartphones.size(); i++) {
//            System.out.printf("\nDetermining constraint satisfaction for the %s\n", allSmartphones.get(i).getName());
            temp = findMostSatisfiedConstraints(tree, allSmartphones.get(i), "");
//            System.out.printf("%s satisfied %d constraints\n", allSmartphones.get(i).getName(), temp);

            if (temp > max) {
                max = temp;

                remainder.clear();
                remainder.add(allSmartphones.get(i));
            } else if (temp == max) {
                remainder.add(allSmartphones.get(i));
            }
        }

        return remainder;
    }

    /*
        This is a depth-first-search of the ConstraintTree param
     */
    private int findMostSatisfiedConstraints(ConstraintTree tree, Smartphone sp, String tabs) {
        int numOfSatisfied;

        if (determineConstraintSatisfaction(tree.getConstraint(), sp)) {
//            System.out.printf("%s %s was satisfied\n", tabs, tree.getConstraint().getConstraintType());
            numOfSatisfied = 1;

            if (tree.getChildren() != null) {
                for (int i = 0; i < tree.getChildren().size(); i++) {
                    int temp = findMostSatisfiedConstraints(tree.getChildren().get(i), sp, tabs+"\t");
                    if (temp >= numOfSatisfied) {
                        numOfSatisfied = temp + 1;
                    }
                }
            }

            return numOfSatisfied;
        } else {
//            System.out.printf("%s %s was not satisfied\n", tabs, tree.getConstraint().getConstraintType());
            return 0;
        }
    }

    private boolean determineConstraintSatisfaction(Constraint constraint, Smartphone sp) {
        switch (constraint.getConstraintType()) {
            case HEAD_TAUTOLOGY:
                return true;
            case BATTERY:
                return constraint.getValue() == sp.getBattery();
            case CAMERA:
                return constraint.getValue() == sp.getCamera();
            case DISPLAY_SIZE:
                return constraint.getValue() == sp.getDisplaySize();
            case PRICE:
                return constraint.getValue() == sp.getPrice();
            case RAM:
                return constraint.getValue() == sp.getRam();
            case RESOLUTION:
                return constraint.getValue() == sp.getDisplayResolution();
            case STORAGE:
                return constraint.getValue() == sp.getStorage();
            default:
                return false;
        }
    }

    private List<Constraint> buildConstraints(UserConstraint uC) {
        Percentiles per = new Percentiles();
        NormalizedValue price = convertFromWithInt(uC.getPrice(), per.getPricePercentile());
        NormalizedValue battery = convertFromWithInt(uC.getBattery(), per.getBatteryPercentile());
        NormalizedValue camera = convertFromWithInt(uC.getCamera(), per.getCameraPercentile());
        NormalizedValue ram = convertFromWithDouble(uC.getRam(), per.getRamPercentile());
        NormalizedValue storage = convertFromWithInt(uC.getStorage(), per.getStoragePercentile());
        NormalizedValue resolution = convertFromWithInt(uC.getResolution(), per.getDisplayResolutionPercentile());
        NormalizedValue displaySize = convertFromWithDouble(uC.getDisplaySize(), per.getDisplaySizePercentile());

        Constraint headTautology = new Constraint(ConstraintType.HEAD_TAUTOLOGY, NormalizedValue.FIVE);
        Constraint batteryConstraint = new Constraint(ConstraintType.BATTERY, battery);
        Constraint cameraConstraint = new Constraint(ConstraintType.CAMERA, camera);
        Constraint displaySizeConstraint = new Constraint(ConstraintType.DISPLAY_SIZE, displaySize);
        Constraint priceConstraint = new Constraint(ConstraintType.PRICE, price);
        Constraint ramConstraint = new Constraint(ConstraintType.RAM, ram);
        Constraint storageConstraint = new Constraint(ConstraintType.STORAGE, storage);
        Constraint resolutionConstraint = new Constraint(ConstraintType.RESOLUTION, resolution);

        return new ArrayList<Constraint>() {
            {
                add(headTautology);
                add(batteryConstraint);
                add(cameraConstraint);
                add(displaySizeConstraint);
                add(priceConstraint);
                add(ramConstraint);
                add(resolutionConstraint);
                add(storageConstraint);
            }
        };
    }

    private ConstraintTree buildConstraintTree(Constraint headConstraint, List<Constraint> constraints) {
        ConstraintTree tree = new ConstraintTree(new ArrayList<>(), headConstraint);

        constraints.remove(headConstraint);
        if (constraints.size() > 0) {
            constraints.forEach(c -> {
                ConstraintTree subTree = buildConstraintTree(c, new ArrayList<>(constraints));
                tree.addChild(subTree);
            });
        } else {
            tree.setChildren(null);
        }

        return tree;
    }


//    List<Smartphone> remainder = csSmartphones
//            .stream()
//            .filter(sp ->
//                    sp.getPrice().ordinal() + sp.getCamera().ordinal() == price.ordinal() + camera.ordinal() &&
//                            sp.getDisplaySize().ordinal() + sp.getBattery().ordinal() == displaySize.ordinal() + battery.ordinal() &&
//                            sp.getDisplayResolution().ordinal() + sp.getPrice().ordinal() == resolution.ordinal() + price.ordinal()
//            )
//            .collect(Collectors.toList());
}
