(function() {
    angular.module("synxApp")
        .controller("subforumController", ["$scope", "subforumPromise", "topicsPromise", function($scope, subforumPromise, topicsPromise) {

            $scope.subforum = subforumPromise.data;
            $scope.topics = topicsPromise.data;
        }]);
})();