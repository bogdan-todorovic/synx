(function() {
    angular.module("synxApp")
        .controller("subforumController", ["$scope", "$rootScope", "$state", "subforumPromise", "topicsPromise", "userService", "subforumService", "$filter", function($scope, $rootScope, $state,subforumPromise, topicsPromise, userService, subforumService, $filter) {

            $scope.subforum = subforumPromise.data;
            $scope.topics = topicsPromise.data;
            $scope.filteredTopics = topicsPromise.data;

            $scope.isSubscribed = false;
            // checking if user is subscribed to current subforum
            (function() {
                if ($rootScope.user != null) {
                    var followedSubforums = $rootScope.user.followedSubforums;
                    var currentSubforum = $scope.subforum.title;
                    for (var i = 0; i < followedSubforums.length; i++) {
                        if (followedSubforums[i] === currentSubforum) {
                            $scope.isSubscribed = true;
                        }
                    }
                }
            })();

            $scope.subscribe = function() {
                $rootScope.user.followedSubforums.push($scope.subforum.title);
                $scope.isSubscribed = true;
                updateUser();
            };

            $scope.unsubscribe = function() {
                var index = $rootScope.user.followedSubforums.indexOf($scope.subforum.title);
                if (index > -1) {
                    $rootScope.user.followedSubforums.splice(index,1);
                    $scope.isSubscribed = false;
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

            $scope.delete = function() {
                subforumService.removeSubforum($scope.subforum.title)
                    .then(function(response) {
                        $state.go("home");
                    });
            };

            $scope.search = function(searchText) {
                $scope.filteredTopics = $filter("filter")($scope.topics, searchText);
            };
        }]);
})();