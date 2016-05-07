package form.data;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;

public abstract class ADataGetter implements IDataGetter {
	
	@Override
	public Component getComponent(Component component, @SuppressWarnings("rawtypes") Class c) {
		if (c.isInstance(component))
			return component;

		if (component instanceof Container) {
			Container container = (Container) component;
			for (int i = 0; i < container.getComponentCount(); i++) {
				Component comp2 = getComponent(container.getComponent(i), c);
				if (comp2 != null)
					return comp2;
			}
		}
		return null;
	}
	

}
