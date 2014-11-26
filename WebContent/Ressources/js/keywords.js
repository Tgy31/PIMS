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
    
    self.fetchKeywords = function() {
		$.ajax({
			url: '/PIMS/keywords/?type=student&id=1488913&content=json'
		}).done(function() {
			console.log( "success" );
		}).fail(function() {
		    alert( "error" );
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
    self.loadKeywords();
}

ko.applyBindings(new KeywordViewModel());