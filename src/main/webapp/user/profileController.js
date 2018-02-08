(function() {
    angular.module("synxApp")
        .controller("profileController", ["user", "savedTopics", "savedComments", "messages", "upvotedTopics", "downvotedTopics", "$scope", function(user, savedTopics, savedComments, messages, upvotedTopics, downvotedTopics, $scope) {
            
            $scope.user = user;
            $scope.savedTopics = savedTopics;
            $scope.savedComments = savedComments;
            $scope.messages = messages;
            $scope.upvotedTopics = upvotedTopics;
            $scope.downvotedTopics = downvotedTopics;
        }]);
})();