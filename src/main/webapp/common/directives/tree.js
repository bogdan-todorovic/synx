(function() {
    angular.module("synxApp")
        .directive("tree", function(recursionHelperService, commentService, $rootScope, userService) {
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
                            scope.temp = {};
                            scope.temp.lead = $rootScope.commentSubforum.leadModerator;
                            
                            scope.show = false;
                            scope.toggleShow = function() {
                                scope.show = !scope.show;
                            };

                            scope.editMode = false;
                            scope.toggleEditMode = function() {
                                scope.editMode = !scope.editMode;
                            }

                            scope.newSubcomment = {};
                            scope.newSubcomment.parentComment = scope.root.id;
                            scope.newSubcomment.topic = scope.root.topic;

                            scope.createSubcomment = function() {
                                scope.newSubcomment.author = $rootScope.user.username;
                                commentService.createComment(scope.newSubcomment)
                                    .then(function(response) {
                                        scope.root.subcomments.push(response.data);
                                    }, function(error) {
                                        console.log(error);
                                    });
                            };

                            
                            scope.temp.newcontent = scope.root.content;
                            scope.update = function() {
                                if ($rootScope.user.username != scope.lead) {
                                    scope.root.edited = true;
                                }
                                scope.root.content = scope.temp.newcontent;
                                console.log(scope.root);
                                commentService.update(scope.root.id, scope.root)
                                    .then(function(response) {
                                        scope.toggleEditMode();
                                    });
                            }

                            scope.delete = function() {
                                commentService.delete(scope.root.id)
                                    .then(function(response) {
                                        scope.root.deleted = true;
                                    });
                            };



                            scope.temp.isSaved = false;
                            // checking if user has already saved current comment
                            (function() {
                                if ($rootScope.user != null) {
                                    var savedComments = $rootScope.user.savedComments;
                                    var currentComment = scope.root.id;
                                    for (var i = 0; i < savedComments.length; i++) {
                                        if (savedComments[i] === currentComment) {
                                            scope.temp.isSaved = true;
                                        }
                                    }
                                }
                            })();

                            scope.save = function() {
                                $rootScope.user.savedComments.push(scope.root.id);
                                scope.temp.isSaved = true;
                                updateUser();
                            };

                            scope.unsave = function() {
                                var index = $rootScope.user.savedComments.indexOf(scope.root.id);
                                if (index > -1) {
                                    $rootScope.user.savedComments.splice(index,1);
                                    scope.temp.isSaved = false;
                                    updateUser();
                                }
                            };

                            function updateUser() {
                                userService.updateUser($rootScope.user.username, $rootScope.user)
                                        .then(function(response) {
                                            var user = JSON.stringify(response.data);
                                            localStorage.setItem("currentUser", user);
                                            $rootScope.user = JSON.parse(localStorage.getItem("currentUser"));
                                        });
                            }
                        }
                    });
                }
            }
        });
})();