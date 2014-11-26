// Class to represent a row in the seat reservations grid
var totalIndex = 0;

function Keyword(id, name) {
    var self = this;
    self.name = name;
    self.id = id;
    self.index = totalIndex++;
    console.log(self);
}

function indexOfSelectedKeyword() {
	var input = document.getElementById("keywordSelect");
	var keywordIndex = input.options[input.selectedIndex].value;
	return keywordIndex;
}

// Overall viewmodel for this screen, along with initial state
function ReservationsViewModel() {
    var self = this;

    // Non-editable catalog data - would come from the server
    self.selectedKeywords = ko.observableArray([]); 
    self.availableKeywords = ko.observableArray([]); 

    self.existingKeywords = [
        new Keyword(456, "Mobile"),
        new Keyword(457, "Web"),
        new Keyword(458, "Desktop")
    ];
    ko.utils.arrayPushAll(self.availableKeywords, self.existingKeywords);
    
    // Operations
    self.addKeyword = function() {
    	
    	var index = indexOfSelectedKeyword();
    	var keyword = self.existingKeywords[index];
    	console.log("index:",index,"keyword:", keyword);
    	console.log(self.existingKeywords);
    	
        self.selectedKeywords.push(keyword);
        self.availableKeywords.remove(keyword);
    };
    self.removeKeyword = function(keyword) { 
        self.availableKeywords.push(keyword);
        self.selectedKeywords.remove(keyword);
    };
}

ko.applyBindings(new ReservationsViewModel());