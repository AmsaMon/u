var saveProfile = function(window){
	var bindSaveButton = function($container) {
		$('#js-save-profile').bind("click", function(){
			var data = $('form').serializeArray().reduce(function(obj, item) {
			    obj[item.name] = item.value;
			    return obj;
			}, {});
			//TODO: handle success and errors
			$.post(window.saveProfile(data), function(undefined, status) {
			});
		});
	};

	bindSaveButton($("#js-profile-form-container"));
};
saveProfile(window);

