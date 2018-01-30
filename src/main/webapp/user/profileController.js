(function() {
    angular.module("synxApp")
        .controller("profileController", ["user", "savedTopics", "$scope", function(user, savedTopics, $scope) {
            $scope.user = user;
            $scope.savedTopics = savedTopics;
        }]);
})();