(function() {
    angular.module("synxApp")
        .controller("homeController", ["$scope", "$rootScope", "topics", function($scope, $rootScope, topics) {

            $scope.filteredTopics = [];
            if ($rootScope.user) {
                topics.forEach(function(topic) {
                    var isFollowed = $rootScope.user.followedSubforums.includes(topic.subforum);
                    if (isFollowed) {
                        $scope.filteredTopics.push(topic);
                    }
                });
            }
            else {
                $scope.filteredTopics = topics;
            }
              


        }]);
})();