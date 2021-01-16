import java.lang.Class;
import java.lang.Integer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class ChangeRegistry extends BaseSystem {
  protected Map<Integer, Set<Class<? extends Component>>> toRemove = new HashMap();

  protected Map<Integer, Set<Component>> toMark = new HashMap();

  protected void initialize() {
    E._changeRegistry=this;
  }

  public void processSystem() {
    E._changeRegistry=this;
    toRemove.clear();
    toMark.clear();
  }

  void remove(int entityId, Class componentClass) {
    this.toRemove.computeIfAbsent(entityId, id -> new HashSet()).add(componentClass);
  }

  public Map<Integer, Set<Class<? extends Component>>> getRemoved() {
    return this.toRemove;
  }

  void mark(int entityId, Component component) {
    this.toMark.computeIfAbsent(entityId, id -> new HashSet()).add(component);
  }

  public Map<Integer, Set<Component>> getMarked() {
    return this.toMark;
  }
}
