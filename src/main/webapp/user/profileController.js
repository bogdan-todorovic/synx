(function() {
    angular.module("synxApp")
        .controller("profileController", ["user", "savedTopics", "savedComments", "messages", "upvotedTopics", "downvotedTopics", "upvotedComments", "downvotedComments", "$scope", function(user, savedTopics, savedComments, messages, upvotedTopics, downvotedTopics, upvotedComments, downvotedComments, $scope) {
            
            $scope.user = user;
            $scope.savedTopics = savedTopics;
            $scope.savedComments = savedComments;
            $scope.messages = messages;
            $scope.upvotedTopics = upvotedTopics;
            $scope.downvotedTopics = downvotedTopics;
            $scope.upvotedComments = upvotedComments;
            $scope.downvotedComments = downvotedComments;
        }]);
})();
