(function() {
    angular.module("synxApp")
        .controller("subforumsController", ["$scope", "subforumSvc", function($scope, subforumSvc) {

            $scope.subforums = {};
            $scope.subforum = {};
            $scope.createOrEdit = true;

            function getAll() {
                subforumSvc.getAllSubforums()
                    .then(function(response) {
                            $scope.subforums = response.data;
                        },
                        function() {
                            $scope.alertMessage = "Unable to load subforums";
                        }
                    );
            }
            getAll();

            $scope.submit = function() {
                if ($scope.createOrEdit) {
                    create();
                } 
                else {
                    console.log("APDEJTUJ POSTOJECI");
                    update();
                } 

            };

            var create = function() {
                subforumSvc.createSubforum($scope.subforum)
                    .then(function(response) {
                        $scope.subforums.push(response.data);
                        $scope.successMessage = "Subforum successfully created";
                    }, 
                    function(error) {
                        $scope.errorMessage = "Subforum with that title already exists";
                    });
            };

            var update = function() {
                subforumSvc.updateSubforum($scope.subforum)
                    .then(function(response) {
                        getAll();
                        $scope.successMessage = "Subforum successfully updated";
                    },
                    function(error) {
                        $scope.errorMessage = "Subforum with that title already exists";
                    }
                    );
            };

            $scope.delete = function(sf) {
                console.log(sf);
                subforumSvc.removeSubforum(sf.title)
                    .then(function(response) {
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

            $scope.edit = function(sf) {
                //cloninig object
                $scope.subforum = JSON.parse(JSON.stringify(sf));
                $scope.createOrEdit = false;
            };

            $scope.resetForm = function() {
                $scope.subforum = {};
                $scope.createOrEdit = true;
            };
        }]);
})();