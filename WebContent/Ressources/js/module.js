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
    
    self.keywoardWithID = function(keywordID) {
    	var keyword = null;
    	this.keywords().forEach(function(keywordIte) {
    	    if (keywordIte.id == keywordID) {
    	    	keyword = keywordIte;
    	    }
    	});
    	return keyword;
    };
    
    self.fetchKeywords = function() {
    	var jsonDiv = $('#json-variables');
    	self.handleFetchKeywords(jsonDiv.text());
    };
    
    self.handleFetchKeywords = function(result) {
    	var json = JSON.parse(result);
    	
    	// Reset arrays
    	self.keywords.removeAll();
    	
        // find selected keywords
    	json.forEach(function(name) {
    	    self.keywords.push(new Keyword(name));
    	});
    };
    
    self.submitKeywords = function() {

    	var userType = getParameterByName('type');
    	var userID = getParameterByName('id');
    	var url = '/PIMS/keywords/';
    	var data = {
    		userType: userType,
    		userID: userID,
    		keywords: JSON.stringify(self.selectedKeywords())
    	};
    	
    	$.post(url, data, self.generateSubmitHandler());
    };
    
    self.fetchKeywords();
}

ko.applyBindings(new KeywordViewModel());