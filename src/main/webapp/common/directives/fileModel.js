(function() {
    angular.module("synxApp")
        .directive("fileModel", function() {
            return {
                // directive can only be used as an attribute
                restrict: "A",
                link: function(scope, elements, attrs) {
                    elements.on("change", function(event) {
                        // checking if something is selected
                        if(elements[0].files[0]) { 
                            scope.chosenFile = elements[0].files[0];
                            scope.loadFile();
                        }
                    });
                }
            }
        });
})();