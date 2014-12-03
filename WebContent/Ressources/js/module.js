// Class to present and edit keywords
var totalIndex = 0;
function Keyword(name) {
    var self = this;
    self.name = name;
    self.id = totalIndex++;
}

// Overall viewmodel for this screen, along with initial state
function KeywordViewModel() {
    var self = this;

    self.keywords = ko.observableArray([]); 
    
    // Operations
    self.addKeyword = function() {
    	var nameInput = document.getElementById('inputKeywordName');
    	var name = nameInput.value;
    	
    	if (name && name.length > 0) {
        	var keyword = new Keyword(name);
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
    	json.forEach(function(name) {
    	    self.keywords.push(new Keyword(name));
    	});
    };
    
    self.getKeywords = function() {
    	var keywords = [];
    	self.keywords().forEach(function(keyword) {
    		keywords.push(keyword.name);
    	});
    	return keywords;
    };
    
    self.fetchKeywords();
}

var keywordViewModel = new KeywordViewModel();
ko.applyBindings(keywordViewModel);


document.getElementById("submitButton").onclick = function() {
	var keywordsString = keywordViewModel.getKeywords().toString();
    document.getElementById("inputKeywords").value = keywordsString;
};
