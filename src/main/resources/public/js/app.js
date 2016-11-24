
// create our angular app and inject ngAnimate and ui-router 
// =============================================================================
angular.module('smartphinder', ['ngAnimate', 'ui.router'])

// configuring our routes 
// =============================================================================
.config(function($stateProvider, $urlRouterProvider) {
    
    $stateProvider
    
        // route to show our basic form (/form)
        .state('form', {
            url: '/form',
            templateUrl: 'form.html',
            controller: 'formController'
        })
        
        // nested states 
        // each of these sections will have their own view
        // url will be nested (/form/profile)
        .state('form.brand', {
            url: '/brand',
            templateUrl: 'views/brand.html'
        })
        
        // url will be /form/brand
        .state('form.screensize', {
            url: '/screensize',
            templateUrl: 'views/screensize.html'
        })
        
        // url will be /form/payment
        .state('form.pixeldensity', {
            url: '/pixeldensity',
            templateUrl: 'views/pixeldensity.html'
        })
    
        .state('form.battery', {
            url: '/battery',
            templateUrl: 'views/battery.html'
        })
        
        // url will be /form/interests
        .state('form.ram', {
            url: '/ram',
            templateUrl: 'views/ram.html'
        })
        
        // url will be /form/payment
        .state('form.os', {
            url: '/os',
            templateUrl: 'views/os.html'
        })
    
        .state('form.camera', {
            url: '/camera',
            templateUrl: 'views/camera.html'
        })
        
        // url will be /form/interests
        .state('form.storage', {
            url: '/storage',
            templateUrl: 'views/storage.html'
        })
        
        // url will be /form/payment
        .state('form.price', {
            url: '/price',
            templateUrl: 'views/price.html'
        });
       
    // catch all route
    // send users to the form page 
    $urlRouterProvider.otherwise('/form/brand');
})

// our controller for the form
// =============================================================================
.controller('formController', function($scope, $http) {      
    
    // we will store all of our form data in this object
    $scope.response = {};
  
    //storing user response into a variable in order to pass it through the POST request
    var response = $scope.response;
  
    
  var config = {
                 headers : {
                    'Content-Type': 'application/json;charset=utf-8;'
                 }
               }
  
  
  
    // function to process the form
    $scope.processForm = function() {
      
      console.log(response);
      
      $http.post("/smartphone/preferences", response, config)
      .then(
       function(response){
         // success callback
         console.log(response);
       }, 
       function(response){
         // failure callback
         console.log(response);
       }
    );
         
    };
    
});

