
import {listSimpleDictDatas} from '@/api/system/dict/data';


const dict={
  namespaced:true,
  state:{ //存储空间 下次直接调用
    /**
     * 数据字典 MAP
     * key：数据字典大类枚举值 dictType
     * dictValue：数据字典小类数值 {dictValue: '', dictLabel: ''} 的数组
     */
    dictDatas: {}
  },
  actions:{
    loadDictDatas({commit}){
        listSimpleDictDatas().then(response=>{
          // 设置数据
          const dictDataMap = {}
          response.data.forEach(dictData => {
            // 获得 dictType 层级
            const enumValueObj = dictDataMap[dictData.dictType]
            if (!enumValueObj) {
              dictDataMap[dictData.dictType] = []
            }
            // 处理 dictValue 层级
            dictDataMap[dictData.dictType].push({
              value: dictData.value,
              label: dictData.label
            })
          })
          //储存到store数组中
          commit("SET_DIC_DATAS",dictDataMap);
        })
    }
  },
  mutations:{
     SET_DIC_DATAS:(state,dicDatas)=>{
       state.dictDatas=dicDatas;
     }
  }
}


export default  dict;
