(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "subforumSvc", function($scope, subforumSvc) {



            (function() {
                subforumSvc.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function() {
                            $scope.alertMessage = "Unable to load subforums";
                        }
                    );
            })();

        }]);
})();