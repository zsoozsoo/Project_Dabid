<template>
  <div class="body main-back">
    <UserProfile :User="person"/>
    
    <v-card class="mx-auto" max-width="290">
          <v-simple-table>
          <template v-slot:default>
            <tbody style="background-color:#3c3f44;">
              <tr
                v-for="item in items"
                :key="item.name"
                @click="menuActionClick(item.action)"
              >
                <td id="eng-font" class="gold-color">{{ item.text }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
    </v-card>

  </div>
</template>
<script>
import UserProfile from "@/components/UserProfile.vue"
import rest from "../../js/httpCommon.js";

export default {
  name: "Mypage",
  components: {
    UserProfile,
  },
  data: function () {
    return {
      dialog: false,
      fontSize: 8,
      person: null,
      userId: null,
      reviews: [],
      items: [
        {
          
          text: "Live",
          action: "goMyLive",
        },
        {
          
          text: "Review",
          action: "goReview",
        },
      ],
    };
  },
  methods: {
    setToken: function () {
      const jwtToken = localStorage.getItem("jwt");
      const config = {
        Authorization: `Bearer ${jwtToken}`,
      };
      return config;
    },
    getProfile: function (userId) {
      rest
        .axios({
          method: "get",
          url: `/dabid/users/${userId}`,
          headers: this.setToken(),
        })
        .then((res) => {
          this.person = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    checkReviews: function () {
      //남이 나에게 써준 리뷰
      rest
        .axios({
          method: "get",
          url: `/dabid/users/checkReview/${this.userId}`,
        })
        .then((res) => {
          this.reviews = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    menuActionClick(action) {
      if (action === "goMyLive") {
        this.$router.push({ name: "MyLiveList", params: { 'userId' : this.userId} });
      } else if (action === "goReview") {
        this.$router.push({ name: 'ReviewList', params: { userId: this.userId }});
      } else if (action === "goNotice") {
        this.$router.push({ name: "Notice" });
      } else if (action === "goHelp") {
        this.$router.push({ name: "Help" });
      }
    },
  },
  created: function () {
    if (localStorage.getItem("jwt")) {
      this.userId = this.$route.params.userId
      console.log('받아온 ID', this.userId)
      this.getProfile(this.userId);
      this.checkReviews
    } else {
      this.$router.push({ name: "Login" });
    }
  },
};
</script>

<style scoped>
.card-text {
  display: flex;
  flex-direction: column;
}
.body {
  background-color: #151618;
}
</style>