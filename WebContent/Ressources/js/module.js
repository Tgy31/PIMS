// Class to present and edit keywords
function Keyword(id, name) {
    var self = this;
    self.name = name;
    self.id = id;
}

// Overall viewmodel for this screen, along with initial state
function KeywordViewModel() {
    var self = this;

    self.keywords = ko.observableArray([]); 
    
    self.keywoardWithID = function(keywordID) {
    	var keyword = null;
    	this.keywords().forEach(function(keywordIte) {
    	    if (keywordIte.id == keywordID) {
    	    	keyword = keywordIte;
    	    }
    	});
    	return keyword;
    };
    
    // Operations
    self.addKeyword = function() {
    	var nameInput = document.getElementById('inputKeywordName');
    	var name = nameInput.value;
    	
    	if (name && name.length > 0) {
        	var keyword = new Keyword(-1, name);
        	nameInput.value = "";
            self.keywords.push(keyword);
    	}
    };
    
    self.removeKeyword = function(keyword) { 
        self.keywords.remove(keyword);
    };
    
    self.fetchKeywords = function() {
    	var jsonDiv = $('#json-variables');
    	var json = JSON.parse(jsonDiv.text());
    	
    	// Reset arrays
    	self.keywords.removeAll();
    	json.forEach(function(info) {
    	    self.keywords.push(new Keyword(info.id, info.name));
    	});
    };
    
    self.getKeywords = function() {
    	return JSON.stringify(keywordViewModel.keywords());
    };
    
    self.fetchKeywords();
}

var keywordViewModel = new KeywordViewModel();
ko.applyBindings(keywordViewModel);


document.getElementById("submitButton").onclick = function() {
	var keywordsString = keywordViewModel.getKeywords();
    document.getElementById("inputKeywords").value = keywordsString;
};
