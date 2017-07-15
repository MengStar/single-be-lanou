/**
 *  action 工厂
 */
export default function actionFactory(type) {
    return function (data) {
        return {
            type: type, value: data
        }
    }
}
