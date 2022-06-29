package concurrent.context;

public class ActionContext {

    private static final ThreadLocal<Context> context = ThreadLocal.withInitial(Context::new);

    static class Context {
        private Configuration configuration;
        private OtherResource otherResource;

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public OtherResource getOtherResource() {
            return otherResource;
        }

        public void setOtherResource(OtherResource otherResource) {
            this.otherResource = otherResource;
        }
    }
}
