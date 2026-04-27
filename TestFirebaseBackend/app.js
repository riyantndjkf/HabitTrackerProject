var admin = require("firebase-admin");
var serviceAccount = require("./serviceAccountKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});
const registrationToken =
  "eUxiWqnoROCX7Tf0Oceli1:APA91bHPjbVRjZ3SucfqxZWfNMeMmOIs3he8oUL9vc16cBqsIZzK-1wA7BoRuRJne36zuPDPFgoUC_zPPWHnYe1c5U-M3T_iL4l_p0SPpMRQHMdssna_C5w";

const message = {
  notification: {
    title: 'Notifikasi Sukses! 🎉',
    body: 'Ini adalah pesan dari backend Node.js'
  },
  data: {
    title: 'Data Masuk!',
    body: 'Pesan ini ditangkap oleh Broadcast Receiver saat app dibuka'
  },
  token: registrationToken
};

// Mengirim pesan ke device
admin.messaging().send(message)
  .then((response) => {
    console.log('Berhasil mengirim pesan:', response);
  })
  .catch((error) => {
    console.log('Gagal mengirim pesan:', error);
  });