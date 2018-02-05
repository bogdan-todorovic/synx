(function() {
    angular.module("synxApp")
        .controller("messageController", ["$scope", "$rootScope", "$state", "$stateParams", "messageService", function($scope, $rootScope, $state, $stateParams, messageService) {

            $scope.message = {};
            $scope.message.receiver = $stateParams.receiver;
            
            $scope.send = function() {
                $scope.message.sender = $rootScope.user.username;
                messageService.createMessage($scope.message)
                    .then(function(response) {
                        $scope.alertMessage = "Message sent";
                    });
            };
        }]);
})();