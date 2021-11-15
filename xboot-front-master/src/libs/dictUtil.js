import axios from 'axios';
import { getDictData } from '@/api/index';

let dictUtil = {

};

// 获取常用的数据字典保存至vuex
dictUtil.initDictData = function (vm) {
    axios.get(getDictData + "carTypeA").then(res => {
        if (res.success) {
            vm.$store.commit("setCarTypeAs", res.result);
        }
    });
    axios.get(getDictData + "carTypeB").then(res => {
        if (res.success) {
            vm.$store.commit("setCarTypeBs", res.result);
        }
    });
    axios.get(getDictData + "sex").then(res => {
        if (res.success) {
            vm.$store.commit("setSex", res.result);
        }
    });


    axios.get(getDictData + "belongType").then(res => {
        if (res.success) {
            vm.$store.commit("setBelongTypes", res.result);
        }
    });
    axios.get(getDictData + "useType").then(res => {
        if (res.success) {
            vm.$store.commit("setUseTypes", res.result);
        }
    });
    axios.get(getDictData + "carSieralType").then(res => {
        if (res.success) {
            vm.$store.commit("setCarSieralTypes", res.result);
        }
    });
    axios.get(getDictData + "yearType").then(res => {
        if (res.success) {
            vm.$store.commit("setYearTypes", res.result);
        }
    });
    axios.get(getDictData + "regionGroups").then(res => {
        if (res.success) {
            vm.$store.commit("setRegionGroups", res.result);
        }
    });
    axios.get(getDictData + "clientType").then(res => {
        if (res.success) {
            vm.$store.commit("setClientTypes", res.result);
        }
    });
    axios.get(getDictData + "runCardCarType").then(res => {
        if (res.success) {
            vm.$store.commit("setRunCardCarTypes", res.result);
        }
    });
    axios.get(getDictData + "timeLimitType").then(res => {
        if (res.success) {
            vm.$store.commit("setTimeLimitTypes", res.result);
        }
    });
    axios.get(getDictData + "month").then(res => {
        if (res.success) {
            vm.$store.commit("setMonths", res.result);
        }
    });
    axios.get(getDictData + "clientType").then(res => {
        if (res.success) {
            vm.$store.commit("setClientTypes", res.result);
        }
    });
    axios.get(getDictData + "feeType").then(res => {
        if (res.success) {
            vm.$store.commit("setFeeTypes", res.result);
        }
    });
    axios.get(getDictData + "everyFee").then(res => {
        if (res.success) {
            vm.$store.commit("setEveryFees", res.result);
        }
    });
    axios.get(getDictData + "siteCount").then(res => {
        if (res.success) {
            vm.$store.commit("setSiteCounts", res.result);
        }
    });
    axios.get(getDictData + "insuranceMoney").then(res => {
        if (res.success) {
            vm.$store.commit("setInsuranceMoneys", res.result);
        }
    });
    axios.get(getDictData + "shortDiscount").then(res => {
        if (res.success) {
            vm.$store.commit("setShortDiscounts", res.result);
        }
    });
    axios.get(getDictData + "recieveTypes").then(res => {
        if (res.success) {
            vm.$store.commit("setRecieveTypes", res.result);
        }
    });
    axios.get(getDictData + "orderAgree").then(res => {
        if (res.success) {
            vm.$store.commit("setOrderAgrees", res.result);
        }
    });
    axios.get(getDictData + "supplierLevel").then(res => {
        if (res.success) {
            vm.$store.commit("setSupplierLevels", res.result);
        }
    });
    axios.get(getDictData + "helperOrderPicSort").then(res => {
        if (res.success) {
            vm.$store.commit("setHelperOrderPicSorts", res.result);
        }
    });
};

// 字典值和字典title映射
dictUtil.reflectDictData = function (targetValue, dictData) {
    let targetDic = {}
    dictData.forEach(element => {
        if (element.value == targetValue) {
            targetDic = element;
        }
    });
    return targetDic;
};

dictUtil.reflectDictDataTitle = function (targetTitle, dictData) {
    let targetDic = {}
    dictData.forEach(element => {
        if (element.title == targetTitle) {
            targetDic = element;
        }
    });
    return targetDic;
};

export default dictUtil;
