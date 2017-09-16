(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "subforumSvc", function($scope, subforumSvc) {

            $scope.subforums = {};
            $scope.subforum = {};

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

            $scope.create = function() {
                subforumSvc.createSubforum($scope.subforum)
                    .then(function(response) {
                        $scope.subforums.push(response.data);
                        $scope.successMessage = "Subforum successfully created";
                    }, 
                    function(error) {
                        $scope.errorMessage = "Subforum with that title already exists";
                    });
            };

            $scope.delete = function(sf) {
                console.log(sf);
                subforumSvc.removeSubforum(sf.title)
                    .then(function(response) {
                        console.log("deleted");
                        var index = $scope.subforums.indexOf(sf);
                        if (index !== -1) {
                            $scope.subforums.splice(index, 1); 
                        } 
                    },
                    function(error) {
                        console.log("unsuccessfully");
                    }
                    );
            };

        }]);
})();