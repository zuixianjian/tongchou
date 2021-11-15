import CryptoJS from 'crypto-js/crypto-js'

// 默认的 KEY 与 iv 与后端保持一致 ，不采用后端传值密钥
const KEY = CryptoJS.enc.Utf8.parse('aaABCDEFDfhLZO0z');// 密钥
const IV = CryptoJS.enc.Utf8.parse('412ABCDEFA34303X');// 偏移量  
/**
 * AES加密 ：字符串 key iv  返回base64
 */
export function Encrypt (word, keyStr, ivStr) {
    let key = KEY;
    let iv = IV;

    if (keyStr) {
        key = CryptoJS.enc.Utf8.parse(keyStr);
        iv = CryptoJS.enc.Utf8.parse(ivStr);
    }

    let srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.ZeroPadding
    });

    return CryptoJS.enc.Base64.stringify(encrypted.ciphertext);
}
/**
 * AES 解密 ：字符串 key iv  返回base64
 *
 */
export function Decrypt (word, keyStr, ivStr) {
    let key = KEY;
    let iv = IV;

    if (keyStr) {
        key = CryptoJS.enc.Utf8.parse(keyStr);
        iv = CryptoJS.enc.Utf8.parse(ivStr);
    }

    let base64 = CryptoJS.enc.Base64.parse(word);
    let src = CryptoJS.enc.Base64.stringify(base64);

    var decrypt = CryptoJS.AES.decrypt(src, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.ZeroPadding
    });

    var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
    return decryptedStr.toString();
}