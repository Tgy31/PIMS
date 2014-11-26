var selectedModuleChanged = function() {
	var input = document.getElementById("inputSelectedModule");
	var moduleID = input.options[input.selectedIndex].value;
	window.location.href = "/PIMS/modules/" + moduleID + "/";
};

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}