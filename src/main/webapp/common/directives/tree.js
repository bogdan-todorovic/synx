(function() {
    angular.module("synxApp")
        .directive("tree", function(recursionHelperService, commentService) {
            return {
                restrict: "E",
                templateUrl: "comment/comment.html",
                scope: {
                    "root": "="
                },
                compile: function(element) {
                    return recursionHelperService.compile(element, {
                        pre: function(scope, elements, attrs) {
                        },
                        post: function(scope, elements, attrs) {
                            scope.show = false;
                            scope.toggleShow = function() {
                                scope.show = !scope.show;
                            };

                            scope.newSubcomment = {};
                            scope.newSubcomment.parentComment = scope.root.id;
                            scope.newSubcomment.topic = scope.root.topic;

                            scope.createSubcomment = function() {
                                commentService.createComment(scope.newSubcomment)
                                    .then(function(response) {
                                        scope.root.subcomments.push(response.data);
                                    }, function(error) {
                                        console.log(error);
                                    });
                            }
                        }
                    });
                }
            }
        });
})();