(function() {
    angular.module("synxApp")
        .service("subforumSvc", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/subforums";

            self.getAllSubforums = function() {
                return $http.get(self.baseUrl);
            };

        }]); 
})();