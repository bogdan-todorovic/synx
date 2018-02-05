(function() {
    angular.module("synxApp")
        .controller("profileController", ["user", "savedTopics", "messages", "upvotedTopics", "downvotedTopics", "$scope", function(user, savedTopics, messages, upvotedTopics, downvotedTopics, $scope) {
            
            $scope.user = user;
            $scope.savedTopics = savedTopics;
            $scope.messages = messages;
            $scope.upvotedTopics = upvotedTopics;
            $scope.downvotedTopics = downvotedTopics;
        }]);
})();