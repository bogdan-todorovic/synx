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
        }]);
})();