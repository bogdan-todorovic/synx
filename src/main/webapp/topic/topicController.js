(function() {
    angular.module("synxApp")
        .controller("topicController", ["$scope", "topicPromise", function($scope, topicPromise) {

            $scope.topic = topicPromise.data;
            console.log($scope.topic);
        }]);
})();