// Class to present and edit keywords
var totalIndex = 0;

function Keyword(id, name) {
    var self = this;
    self.name = name;
    self.id = id;
    self.index = totalIndex++;
}

function indexOfSelectedKeyword() {
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
    	
    	var index = indexOfSelectedKeyword();
    	var keyword = self.existingKeywords[index];
    	
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
		$.ajax({
			url: '/PIMS/keywords/?type=student&id=1488913&content=json',
			success: this.handleFetchKeywords,
			error: null
		});
    };
    
    self.handleFetchKeywords = function(result) {
    	var json = JSON.parse(result);
    	console.log(json);
    	
    	json.existingKeywords.forEach(function(keywordInfo) {
    	    console.log(keywordInfo);
    	    self.existingKeywords.push(new Keyword(keywordInfo[0], keywordInfo[1]));
    	});
    	
        ko.utils.arrayPushAll(self.availableKeywords, self.existingKeywords);
    	
    	json.selectedKeywords.forEach(function(keywordInfo) {
    	    console.log(keywordInfo);
    	    var keyword = self.keywoardWithID(keywordInfo[0]);
    	    if (keyword) {
        	    self.addKeyword(keyword);
    	    }
    	});
    };

    self.loadKeywords = function() {
        if (typeof moduleKeywords != 'undefined') {
        	self.existingKeywords = moduleKeywords;
        } else {
        	self.existingKeywords = [];
        }
        self.existingKeywords.push(new Keyword(456, "Mobile"));
        self.existingKeywords.push(new Keyword(456, "Desktop"));
        self.existingKeywords.push(new Keyword(456, "Web"));
        ko.utils.arrayPushAll(self.availableKeywords, self.existingKeywords);
    };
    
    self.fetchKeywords();
}

ko.applyBindings(new KeywordViewModel());