// 代码生成时间: 2025-10-21 06:46:38
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

// 游戏资源管理类
public class GameResourceManager {

    // 资源存储结构，这里简单使用HashMap模拟
    private HashMap<String, Integer> resourcesMap = new HashMap<>();

    /**
     * 添加资源到管理器
     * @param resourceName 资源名称
     * @param amount 资源数量
     * @return boolean 成功或失败
     */
    public boolean addResource(String resourceName, int amount) {
        if (resourceName == null || amount < 1) {
            // 非法参数
            return false;
        }
        resourcesMap.put(resourceName, resourcesMap.getOrDefault(resourceName, 0) + amount);
        return true;
    }

    /**
     * 从管理器中移除资源
     * @param resourceName 资源名称
     * @param amount 需要移除的资源数量
     * @return boolean 成功或失败
     */
    public boolean removeResource(String resourceName, int amount) {
        if (resourceName == null || amount < 1 || !resourcesMap.containsKey(resourceName) ||
                resourcesMap.get(resourceName) < amount) {
            // 非法参数或资源不足
            return false;
        }
        resourcesMap.put(resourceName, resourcesMap.get(resourceName) - amount);
        return true;
    }

    /**
     * 获取指定资源的数量
     * @param resourceName 资源名称
     * @return int 资源数量
     */
    public int getResourceAmount(String resourceName) {
        return resourcesMap.getOrDefault(resourceName, 0);
    }

    /**
     * 检查资源是否存在
     * @param resourceName 资源名称
     * @return boolean 资源是否存在
     */
    public boolean isResourcePresent(String resourceName) {
        return resourcesMap.containsKey(resourceName);
    }

    /**
     * 显示所有资源及其数量
     * @return String 所有资源及其数量的字符串表示
     */
    public String displayAllResources() {
        return resourcesMap.toString();
    }
}
