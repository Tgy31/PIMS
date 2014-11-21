var selectedModuleChanged = function() {
	var input = document.getElementById("inputSelectedModule");
	var moduleID = input.options[input.selectedIndex].value;
	window.location.href = "/PIMS/modules/" + moduleID + "/";
};