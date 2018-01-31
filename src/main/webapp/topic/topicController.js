(function() {
    angular.module("synxApp")
        .controller("topicController", ["$scope", "$rootScope", "$state", "topicPromise", "commentsPromise", "commentService", "userService", "topicService", "subforumService", function($scope, $rootScope, $state, topicPromise, commentsPromise, commentService, userService, topicService, subforumService) {

            $scope.topic = topicPromise.data;
            $scope.comments = commentsPromise.data;

            (function() {
                subforumService.getSubforum($scope.topic.subforum)
                    .then(function(response) {
                        $scope.subforum = response.data;
                    });
            })();

            $scope.newRootComment = {};
            $scope.newRootComment.topic = $scope.topic.title;

            $scope.createRootComment = function() {
                commentService.createComment($scope.newRootComment)
                    .then(function(response) {
                        $scope.comments.push(response.data);
                    }, function (error) {
                        console.log(error);
                    });
            };

            $scope.isSaved = false;
            // checking if user has already saved current subforum
            (function() {
                if ($rootScope.user != null) {
                    var savedTopics = $rootScope.user.savedTopics;
                    var currentTopic = $scope.topic.title;
                    for (var i = 0; i < savedTopics.length; i++) {
                        if (savedTopics[i] === currentTopic) {
                            $scope.isSaved = true;
                        }
                    }
                }
            })();

            $scope.save = function() {
                $rootScope.user.savedTopics.push($scope.topic.title);
                $scope.isSaved = true;
                updateUser();
            }

            $scope.unsave = function() {
                var index = $rootScope.user.savedTopics.indexOf($scope.topic.title);
                if (index > -1) {
                    $rootScope.user.savedTopics.splice(index,1);
                    $scope.isSaved = false;
                    updateUser();
                }
            }

            function updateUser() {
                userService.updateUser($rootScope.user.username, $rootScope.user)
                        .then(function(response) {
                            var user = JSON.stringify(response.data);
                            localStorage.setItem("currentUser", user);
                            $rootScope.user = JSON.parse(localStorage.getItem("currentUser"));
                        });
            }

            $scope.delete = function() {
                console.log("deleted");
                topicService.deleteTopic($scope.topic.title)
                    .then(function(response) {
                        var subforum = $scope.topic.subforum;
                        $state.go("subforum.detail", {title: subforum});
                    });
            };
            
            $scope.isLiked = false;
            $scope.isDisliked = false;

            // checking if user has already liked/disliked current topic
            (function () {
                if ($rootScope.user != null) {
                    var likedTopics = $rootScope.user.likedTopics;
                    var dislikedTopics = $rootScope.user.dislikedTopics;
                    var topic = $scope.topic.title; 
                    for (var i = 0; i < likedTopics.length; i++) {
                        if (likedTopics[i] === topic) {
                            $scope.isLiked = true;
                        }
                    }
                    for (var i = 0; i < dislikedTopics.length; i++) {
                        if (dislikedTopics[i] === topic) {
                            $scope.isDisliked = true;
                        }
                    }
                }
            })();
            // TODO: UPDATE TOPIC
            $scope.toggleLike = function() {
                if (!$scope.isLiked && !$scope.isDisliked) {
                    $scope.isLiked = true;
                    $rootScope.user.likedTopics.push($scope.topic.title);
                    updateUser();
                    $scope.topic.numberOfLikes += 1;
                    updateTopic();
                }
                else if(!$scope.isLiked && $scope.isDisliked) {
                    $scope.isDisliked = false;
                    var index = $rootScope.user.dislikedTopics.indexOf($scope.topic.title);
                    if (index > -1) {
                        $rootScope.user.dislikedTopics.splice(index,1);
                        updateUser();
                        $scope.topic.numberOfDislikes -= 1;
                        updateTopic();
                    }
                }
                
            };

            $scope.toggleDislike = function() {
                if (!$scope.isLiked && !$scope.isDisliked) {
                    $scope.isDisliked = true;
                    $rootScope.user.dislikedTopics.push($scope.topic.title);
                    updateUser();
                    $scope.topic.numberOfDislikes += 1;
                    updateTopic();
                }
                else if ($scope.isLiked && !$scope.isDisliked) {
                    $scope.isLiked = false;
                    var index = $rootScope.user.likedTopics.indexOf($scope.topic.title);
                    if (index > -1) {
                        $rootScope.user.likedTopics.splice(index,1);
                        updateUser();
                        $scope.topic.numberOfLikes -= 1;
                        updateTopic();
                    }
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

            function updateTopic() {
                topicService.updateTopic($scope.topic.title, $scope.topic)
                    .then(function(response) { 
                       $scope.topic = response.data; 
                    });
            }

        }]);
})();