angular.module('index', []).controller('home', function($http,$scope) {
    var self = this;
    $http.get('user/').then(function(response) {
        self.user = response.data;
    })
});
