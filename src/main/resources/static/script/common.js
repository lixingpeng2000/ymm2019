
//校验函数
function isPoneAvailable(str) {
            var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(str)) {
                return false;
            } else {
                return true;
            }
        }
function isMailAvailable(str) {
            var mailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
            if (!mailReg.test(str)) {
                return false;
            } else {
                return true;
            }
        }




