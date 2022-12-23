import React from 'react';
import DefaultProfileImage from './DefaultProfileImage';
import LikeButton from './LikeButton';

const PostView = props => {
    const {post } = props;
    const {user, content} =post;
    const {username,mail,image} = props;
    return (
        <div className='card p-1 border border-success mb-1'>
            <DefaultProfileImage image ={image} width="32" heigh="32" className= "rounded-circle"  />
            <span>{username}</span>
            <div>
                <b>Title:</b>  {post.contentTitle}
            </div>

            <div>
                <a  href={'' + post.contentLink} target="_blank" rel="noreferrer"> 
                    <button >Post Link</button>
                    
                </a>
            </div>
           
            <div>
                <b>Content:</b>  {post.contentPost}
            </div>
            
            
            <div>
                <b>Label:</b>  {post.contentLabel}
            </div>
            
            
            <LikeButton/>
         
        </div>
    );
};

export default PostView;