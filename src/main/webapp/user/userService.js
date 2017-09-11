(function() {
    angular.module("synxApp")
        .service("userSvc", ["$http", function($http) {

            var self = this;
            self.urlBase = "rest/users";

            self.authenticateUser = function(data) {
                return $http.post(self.urlBase + "/login", data);
            };
        }]);
})();