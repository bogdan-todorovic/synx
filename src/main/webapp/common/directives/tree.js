(function() {
    angular.module("synxApp")
        .directive("tree", function(recursionHelperService) {
            return {
                restrict: "E",
                templateUrl: "comment/comment.html",
                scope: {
                    "root": "=",
                    "test": "&"
                },
                compile: function(element) {
                    return recursionHelperService.compile(element, {
                        pre: function(scope, elements, attrs) {
                        },
                        post: function(scope, elements, attrs) {
                            console.log("post-linking");
                            console.log(scope);
                            scope.show = false;
                            scope.toggleShow = function() {
                                scope.show = !scope.show;
                            };
                        }
                    });
                }
            }
        });
})();