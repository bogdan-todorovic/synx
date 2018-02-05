(function() {
    angular.module("synxApp")
        .controller("profileController", ["user", "savedTopics", "messages", "$scope", function(user, savedTopics, messages, $scope) {
            $scope.user = user;
            $scope.savedTopics = savedTopics;
            $scope.messages = messages;
            console.log($scope.messages);

            for (var user in messages) {
                console.log(messages[user]);
            }
        }]);
})();