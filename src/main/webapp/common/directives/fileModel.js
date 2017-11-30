(function() {
    angular.module("synxApp")
        .directive("fileModel", function() {
            return {
                // directive can only be used as an attribute
                restrict: "A",
                link: function(scope, elements, attrs) {
                    console.log(attrs.fileModel);
                    elements.on("change", function() { 
                        console.log(elements[0].files[0]);
                    });
                }
            }
        });
})();