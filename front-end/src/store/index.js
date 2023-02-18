import { createStore } from 'vuex'

export default createStore({
  state: {
    blockSelectList: [],
  },
  getters: {
    getBlockSelectList: state => {
        return state.blockSelectList;
    }
  },
  mutations: {
    setBlockSelectList(state, blockSelectList) {
        state.blockSelectList = blockSelectList;
    }
  },
  actions: {
  },
  modules: {
  }
})
