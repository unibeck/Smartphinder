$('.submit').on('click', function() {

	// set token globally
	//$.fn.fonoApi.options.token = "xxx";

	$('.api').fonoApi({
		token : "86b89476caaf66eda3f21279b7711afc",
		device : $('.devname').val(),
		limit : 10,
		template : function() {

			// argument contains the data object // *returns html content
			return $.map(arguments, function(obj, i) {

				content  = '<h3>'+ obj.DeviceName + '</h3>';
				content += '<table class="table table-striped" style="width:100%">';
				content += '<tr><th>info</th><th>Description</th></tr>';
				
				for(var key in obj){
				  content += "<tr><td>" + key + "</td><td>" + obj[key] + "</td><tr>";
				}

				content += "</table>";
				return $('<div class="table-responsive"></div>').append(content);
			});

		}
	});

});