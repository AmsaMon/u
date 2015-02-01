var saveProfile = function(window){
	var bindSaveButton = function($container) {
		$('#js-save-profile').bind("click", function(){
			var data = $('form').serializeArray().reduce(function(obj, item) {
			    obj[item.name] = item.value;
			    return obj;
			}, {});
			$.post(window.saveProfile(data), function(undefined, status) {
				//alert("Status: " + status);
			});
		});
	};

	bindSaveButton($("#js-profile-form-container"));
};
saveProfile(window);

