(function() {
    angular.module("synxApp")
        .controller("topicController", ["$scope", "topicPromise", "commentsPromise", "commentService", function($scope, topicPromise, commentsPromise, commentService) {

            $scope.topic = topicPromise.data;
            console.log($scope.topic);

            $scope.comments = commentsPromise.data;
            console.log($scope.comments);

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
        }]);
})();