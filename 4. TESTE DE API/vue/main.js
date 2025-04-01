const { createApp, ref } = Vue;

createApp({
  setup() {
    const searchQuery = ref('');
    const results = ref([]);
    const loading = ref(false);
    const error = ref('');
    const searched = ref(false);
    
    const API_URL = 'http://localhost:5000/search';

    const searchOperators = async () => {
      if (!searchQuery.value.trim()) return;
      
      loading.value = true;
      error.value = '';
      searched.value = true;
      
      try {
        const { data } = await axios.get(API_URL, {
          params: { query: searchQuery.value }
        });
        results.value = data;
      } catch (err) {
        console.error('Error:', err);
        error.value = 'Failed to fetch results. Please ensure the server is running.';
        results.value = [];
      } finally {
        loading.value = false;
      }
    };
    
    return { searchQuery, results, loading, error, searched, searchOperators };
  }
}).mount('#app');
