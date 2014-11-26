// Class to present and edit keywords
function Keyword(id, name) {
    var self = this;
    self.name = name;
    self.id = id;
}

function idOfSelectedKeyword() {
	var input = document.getElementById("keywordSelect");
	var keywordIndex = input.options[input.selectedIndex].value;
	return keywordIndex;
}

// Overall viewmodel for this screen, along with initial state
function KeywordViewModel() {
    var self = this;

    self.existingKeywords = [];
    // Non-editable catalog data - would come from the server
    self.selectedKeywords = ko.observableArray([]); 
    self.availableKeywords = ko.observableArray([]); 
    
    // Operations
    self.addKeyword = function() {
    	
    	var id = idOfSelectedKeyword();
    	var keyword = self.keywoardWithID(id);
    	
        self.selectedKeywords.push(keyword);
        self.availableKeywords.remove(keyword);
    };
    
    self.removeKeyword = function(keyword) { 
        self.availableKeywords.push(keyword);
        self.selectedKeywords.remove(keyword);
    };
    
    self.keywoardWithID = function(keywordID) {
    	var keyword = null;
    	this.existingKeywords.forEach(function(keywordIte) {
    	    if (keywordIte.id == keywordID) {
    	    	keyword = keywordIte;
    	    }
    	});
    	return keyword;
    };
    
    self.fetchKeywords = function() {
    	var userType = getParameterByName('type');
    	var userID = getParameterByName('id');
    	var contentType = 'json';
		$.ajax({
			url: '/PIMS/keywords/?type=' + userType + '&id=' + userID +'&content='+ contentType,
			success: this.handleFetchKeywords,
			error: null
		});
    };
    
    self.handleFetchKeywords = function(result) {
    	var json = JSON.parse(result);
    	
    	
    	// read exisiting keywords
    	json.existingKeywords.forEach(function(keywordInfo) {
    	    console.log(keywordInfo);
    	    self.existingKeywords.push(new Keyword(keywordInfo[0], keywordInfo[1]));
    	});
    	
    	// Reset arrays
    	self.availableKeywords.removeAll();
    	self.selectedKeywords.removeAll();
        ko.utils.arrayPushAll(self.availableKeywords, self.existingKeywords);
    	
        // find selected keywords
    	json.selectedKeywords.forEach(function(keywordInfo) {
    	    var keyword = self.keywoardWithID(keywordInfo[0]);
    	    if (keyword) {
        	    self.addKeyword(keyword);
    	    }
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
    	
    	$.post(url, data, self.HandleSubmitKeywords);
    };
    
    self.HandleSubmitKeywords = function(data, status) {
    	console.log(data);
    	console.log(status);
    };
    
    self.fetchKeywords();
}

ko.applyBindings(new KeywordViewModel());