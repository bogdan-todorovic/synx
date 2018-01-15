(function() {
    angular.module("synxApp")
        .controller("topicController", ["$scope", "topicPromise", "commentsPromise", function($scope, topicPromise, commentsPromise) {

            $scope.topic = topicPromise.data;
            console.log($scope.topic);

            $scope.comments = commentsPromise.data;
            console.log($scope.comments);
        }]);
})();