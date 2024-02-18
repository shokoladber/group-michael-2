import React from 'react'
import pix3 from  '../../Components/Assets/blog1.jpg';
import './Blog.css';
import BlogPost from '../../Components/common/blogpost/BlogPost';

function Blog() {
  return (
   
    <div>
              
            <div >
                          
              <BlogPost />
              <div>
                <h1>My Blog Post</h1>
                <p>This is my blog post content.</p>
                  <img src={pix3} alt="blog" />
              </div>
            
                        
                  Lorem ipsum dolor, sit amet consectetur adipisicing elit. Porro voluptate natus possimus nisi! Tempora voluptatibus expedita neque sed quos explicabo velit voluptatum repudiandae voluptatem. Reiciendis quaerat ducimus quis, error dolorem unde quos nemo! Quasi officiis esse placeat ratione, est reprehenderit ea cum! Consectetur illum cupiditate impedit, delectus ea, voluptatum veritatis molestias similique maxime, repudiandae iusto molestiae necessitatibus! Quod temporibus natus nostrum ex nisi accusantium amet cupiditate corporis, vitae animi? Consectetur nesciunt dolore tenetur voluptas incidunt. Pariatur eaque ea id. Fugiat nesciunt at vitae exercitationem excepturi ex, quaerat et voluptas, nihil porro blanditiis sunt quo modi numquam quam perferendis distinctio ea.

                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Nihil, facere vel placeat quia ratione dolor pariatur qui? Sed maiores neque, deleniti aut magni doloremque natus expedita saepe minus dolore cumque?
            </div>
      
    </div>
  )
}
export default Blog;