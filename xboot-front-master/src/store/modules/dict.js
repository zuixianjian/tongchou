const dict = {
    state: {
        // 经常需要读取的数据字典
        sex: [],
        carTypess: [],
        belongTypess: [],
        useTypess: [],
        carSieralTypess: [],
        yearTypess: [],
        regionGroupss: [],
        clientTypes: [],
        runCardCarTypes: [],
        timeLimitTypes: [],
        months: [],
        clientTypes: [],
        carTypeAs: [],
        carTypeBs: [],
        everyFees: [],
        siteCounts: [],
        insuranceMoneys: [],
        shortDiscounts: [],
        recieveTypes: [],
        orderAgrees: [],
        supplierLevels: [],
        helperOrderPicSorts: []

    },
    mutations: {
        // 设置值的改变方法
        setSex (state, list) {
            state.sex = list;
        },
        setCarTypeAs (state, list) {
            state.carTypeAs = list;
        },
        setCarTypeBs (state, list) {
            state.carTypeBs = list;
        },
        setBelongTypes (state, list) {
            state.belongTypess = list;
        },
        setUseTypes (state, list) {
            state.useTypess = list;
        },
        setCarSieralTypes (state, list) {
            state.carSieralTypess = list;
        },
        setYearTypes (state, list) {
            state.yearTypess = list;
        },
        setRegionGroups (state, list) {
            state.regionGroupss = list;
        },
        setClientTypes (state, list) {
            state.clientTypes = list;
        },
        setRunCardCarTypes (state, list) {
            state.runCardCarTypes = list;
        },
        setTimeLimitTypes (state, list) {
            state.timeLimitTypes = list;
        },
        setMonths (state, list) {
            state.months = list;
        },
        setClientTypes (state, list) {
            state.clientTypes = list;
        },
        setFeeTypes (state, list) {
            state.feeTypes = list;
        },
        setEveryFees (state, list) {
            state.everyFees = list;
        },
        setSiteCounts (state, list) {
            state.siteCounts = list;
        },
        setInsuranceMoneys (state, list) {
            state.insuranceMoneys = list;
        },
        setShortDiscounts (state, list) {
            state.shortDiscounts = list;
        },
        setRecieveTypes (state, list) {
            state.recieveTypes = list;
        },
        setOrderAgrees (state, list) {
            state.orderAgrees = list;
        },
        setSupplierLevels (state, list) {
            state.supplierLevels = list;
        },
        setHelperOrderPicSorts (state, list) {
            state.helperOrderPicSorts = list;
        },

    }
};

export default dict;
