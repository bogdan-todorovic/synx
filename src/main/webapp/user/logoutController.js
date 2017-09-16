(function() {
    angular.module("synxApp")
        .controller("logoutController",["$state", "$rootScope", "$scope", function($state, $rootScope, $scope) {

            $scope.logout = function() {
                localStorage.removeItem("token");
                localStorage.removeItem("currentUser");
                $rootScope.user = null;
                $state.go('home');
            };

        }]);
})();