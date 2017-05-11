angular
    .module('app')
    .controller('UserModalController', UserModalController);

function UserModalController($mdDialog, UserFactory, user, DocumentDownloaderService) {
    var self = this;

    if (user && user.birthday) {
        user.birthday = new Date(user.birthday);
    }

    self.user = user;
    self.saveUser = saveUser;
    self.cancel = cancel;
    self.downloadProfile = downloadProfile;
    self.updateMode = !!self.user;

    self.genders = ['MALE', 'FEMALE'];
    self.roles = ['STUDENT', 'TEACHER', 'ADMIN', 'DISABLED'];

    function saveUser() {
        if (self.updateMode){
            UserFactory.update(self.user, $mdDialog.hide);
        } else {
            UserFactory.save(self.user, $mdDialog.hide);
        }
    }

    function cancel() {
        $mdDialog.cancel();
    }

    function downloadProfile(type) {
        DocumentDownloaderService.downloadDocument('api/users/' + user.id + '/profile/export', type);
    }
}